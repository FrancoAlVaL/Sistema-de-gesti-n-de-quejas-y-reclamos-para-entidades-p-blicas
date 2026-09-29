document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll('select[name="tipoDocumento"], select[name="tipoDocumentoRemitente"]')
        .forEach((select) => {
            const form = select.closest("form");
            const number = form?.querySelector('[name="numeroDocumento"]');
            if (!number) return;
            const updateDocumentRules = () => {
                const isDni = select.value === "DNI";
                number.maxLength = isDni ? 8 : 30;
                number.pattern = isDni ? "[0-9]{8}" : ".{1,30}";
                number.title = isDni ? "El DNI debe contener 8 dígitos." : "Ingresa hasta 30 caracteres.";
            };
            select.addEventListener("change", updateDocumentRules);
            updateDocumentRules();
        });

    document.querySelectorAll("form[data-confirm-password]").forEach((form) => {
        const password = form.querySelector('[name="password"]');
        const confirmation = form.querySelector('[name="passwordConfirm"]');

        form.addEventListener("submit", (event) => {
            if (password && confirmation && password.value !== confirmation.value) {
                event.preventDefault();
                confirmation.setCustomValidity("Las contraseñas no coinciden.");
                confirmation.reportValidity();
                confirmation.focus();
            }
        });

        confirmation?.addEventListener("input", () => confirmation.setCustomValidity(""));
    });

    document.querySelectorAll('input[type="date"][data-no-future]').forEach((input) => {
        const today = new Date();
        input.max = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, "0")}-${String(today.getDate()).padStart(2, "0")}`;
    });

    document.querySelectorAll('input[type="file"][accept*=".pdf"]').forEach((input) => {
        input.addEventListener("change", () => {
            const file = input.files?.[0];
            const allowedExtensions = [...input.accept.matchAll(/\.[a-z0-9]+/gi)].map(([extension]) => extension);
            const validExtension = file && allowedExtensions.some((extension) =>
                file.name.toLowerCase().endsWith(extension.toLowerCase()));
            if (file && (file.size > 10 * 1024 * 1024 || !validExtension)) {
                input.setCustomValidity(`Adjunta un archivo ${allowedExtensions.join(", ")} válido de máximo 10 MB.`);
                input.reportValidity();
                input.value = "";
            } else {
                input.setCustomValidity("");
            }
        });
    });

    document.querySelectorAll("[data-filter-table]").forEach((control) => {
        const table = document.querySelector(control.dataset.filterTable);
        const rows = table?.querySelectorAll("tbody tr");
        if (!rows) return;

        const updateRows = () => {
            const query = (table.closest("[data-filter-scope]")?.querySelector("[data-text-filter]")?.value ?? "")
                .trim().toLocaleLowerCase();
            const status = table.closest("[data-filter-scope]")?.querySelector("[data-status-filter]")?.value ?? "";
            const role = table.closest("[data-filter-scope]")?.querySelector("[data-role-filter]")?.value ?? "";
            const area = table.closest("[data-filter-scope]")?.querySelector("[data-area-filter]")?.value ?? "";
            const active = table.closest("[data-filter-scope]")?.querySelector("[data-active-filter]")?.value ?? "";
            rows.forEach((row) => {
                const matchesText = row.textContent?.toLocaleLowerCase().includes(query) ?? false;
                const matchesStatus = !status || row.dataset.status?.toLocaleLowerCase() === status.toLocaleLowerCase();
                const matchesRole = !role || row.dataset.role === role;
                const matchesArea = !area || row.dataset.area === area;
                const matchesActive = !active || row.dataset.active === active;
                row.hidden = !(matchesText && matchesStatus && matchesRole && matchesArea && matchesActive);
            });
        };

        control.addEventListener("input", updateRows);
        control.addEventListener("change", updateRows);
        const scope = table.closest("[data-filter-scope]");
        scope?.querySelectorAll("[data-text-filter], [data-status-filter], [data-role-filter], [data-area-filter], [data-active-filter]").forEach((filter) => {
            filter.addEventListener("input", updateRows);
            filter.addEventListener("change", updateRows);
        });
        updateRows();
    });

    document.querySelectorAll("[data-clear-filters]").forEach((button) => {
        button.addEventListener("click", () => {
            const scope = button.closest("[data-filter-scope]");
            scope?.querySelectorAll("[data-text-filter], [data-status-filter], [data-role-filter], [data-area-filter], [data-active-filter]").forEach((filter) => {
                filter.value = "";
                filter.dispatchEvent(new Event("input", { bubbles: true }));
            });
        });
    });

    document.querySelectorAll("[data-export-table]").forEach((button) => {
        button.addEventListener("click", () => {
            const table = document.querySelector(button.dataset.exportTable);
            if (!table) return;
            let rows;
            if (table.matches("table")) {
                rows = [...table.querySelectorAll("tr")].filter((row) => !row.hidden);
            } else if (table.id === "tablaEstadisticas" && window.reclamosStats) {
                rows = [["Grupo", "Elemento", "Cantidad"]];
                Object.entries(window.reclamosStats).forEach(([group, values]) => {
                    values.labels.forEach((label, index) => rows.push([group, label, values.data[index]]));
                });
            } else {
                return;
            }
            const csv = rows.map((row) => {
                const cells = row instanceof HTMLTableRowElement ? [...row.cells] : row;
                return cells.map((cell) => {
                    const value = typeof cell === "string" || typeof cell === "number"
                        ? String(cell)
                        : (cell.textContent ?? "").trim();
                    return `"${value.replaceAll('"', '""')}"`;
                }).join(",");
            }).join("\r\n");
            const link = document.createElement("a");
            link.href = URL.createObjectURL(new Blob(["\uFEFF", csv], { type: "text/csv;charset=utf-8" }));
            link.download = "reporte.csv";
            link.click();
            URL.revokeObjectURL(link.href);
        });
    });
});

document.addEventListener("DOMContentLoaded", () => {
    if (typeof Chart === "undefined" || !window.reclamosStats) return;

    const { meses, tipos, estados, areas } = window.reclamosStats;
    const createChart = (id, type, values, dataset, options = {}) => {
        const canvas = document.getElementById(id);
        if (!canvas || !values) return;
        new Chart(canvas, {
            type,
            data: {
                labels: values.labels,
                datasets: [{ ...dataset, data: values.data }]
            },
            options: { responsive: true, ...options }
        });
    };

    createChart("chartMeses", "line", meses, {
        label: "Reclamos",
        borderColor: "#C8102E",
        backgroundColor: "rgba(200,16,46,0.1)",
        fill: true
    });
    createChart("chartTipos", "doughnut", tipos, {
        backgroundColor: ["#C8102E", "#E38F03", "#198754", "#0d6efd", "#6f42c1"]
    });
    createChart("chartEstados", "bar", estados, {
        label: "Cantidad",
        backgroundColor: ["#E38F03", "#0d6efd", "#198754", "#dc3545"]
    });
    createChart("chartAreas", "bar", areas, {
        label: "Reclamos",
        backgroundColor: "#C8102E"
    }, { indexAxis: "y" });
});

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>Daily Member Counts</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <canvas id="dailyMemberChart"></canvas>

    <script>
        // Function to retrieve data from the server
        function fetchData() {
            fetch('/your-server-endpoint') // Replace with your server endpoint
                .then(response => response.json())
                .then(data => {
                    // Process the data and create the chart
                    createChart(data);
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }

        // Function to create the chart
        function createChart(data) {
            // Extract dates and counts from the data
            const dates = data.map(entry => entry.createDate);
            const counts = data.map(entry => entry.count);

            // Create the chart
            const ctx = document.getElementById('dailyMemberChart').getContext('2d');
            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: dates,
                    datasets: [{
                        label: 'Daily Member Counts',
                        data: counts,
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            precision: 0
                        }
                    }
                }
            });
        }

        // Fetch data and create the chart on page load
        document.addEventListener('DOMContentLoaded', function() {
            fetchData();
        });
    </script>
</body>
</html>
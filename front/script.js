fetch("http://localhost:8080/productos")
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error("Error:",error));


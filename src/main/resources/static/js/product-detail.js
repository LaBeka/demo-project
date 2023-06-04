function goDetailsProduct(){
    var productId = (window.location.hash).replace("#",'');
    alert(productId);
    fetchRequest(productId);
}

async function fetchRequest(productId) {
    fetch('http://localhost:8081/product/detail/' + productId)
        .then(response => {
            if (response.status == 404) {
                alert("No product to show");
            } else if (response.status >= 400) {
                throw new Error("Bad response from server");
            }
            return response.json()
        })
        .then(data => {
            createDetailProductCard(data);
        })
        .catch(error => {
            alert("No products to show by this search");
        });
}
function createDetailProductCard(data){
    let container = document.createElement("div");
    container.classList.add("container");
    let row = document.createElement("div");

    row.classList.add("row");
    row.classList.add("row-cols-1");
    row.classList.add("row-cols-md-2");
    row.classList.add("g-1");
    row.classList.add("w-100");
    row.classList.add("py-5");
    container.appendChild(row);

    let bgLight = document.querySelector('.bg-light');
    bgLight.appendChild(container);

    row.innerHTML = `
           <div class="col m-auto">
                <img src="${data.image}" class="img-fluid rounded-start ms-auto w-700 h-450 text-center" alt="Building">
           </div>
           <div class="col">
                <div class="card-body building-list ps-3 pe-6">
                    <div class="icon-headline first">
                        <h5>General builder</h5>
                    </div>
                    <p class="building-text pt-2">${data.description}</p>
                </div>
                <div class="card-body building-list ps-3 pe-6">
                    <div class="icon-headline second">
                        <h5 class="">House Extensions</h5>
                    </div>
                    <p class="building-text pt-2">${data.description}</p>
                </div>
                <div class="card-body building-list ps-3 pe-6">
                    <div class="icon-headline third">
                        <h5 class="">Refubishment</h5>
                    </div>
                    <p class="building-text pt-2">${data.description}</p>
                </div>
           </div>`;

}




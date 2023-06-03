let body = document.querySelector('body');
let page = 0;
let defaultSearchSize = 20;
let totalPages;
let currentPage = document.querySelector('.current-page');

let wordForm = document.getElementById('search-in-word');
let searchValue = document.getElementById('in-word');

let nextButton = document.querySelector('.next');
let prevButton = document.querySelector('.prev');

wordForm.addEventListener('submit', event => {
    event.preventDefault();
    displayProducts();
});
function displayProducts(){
    let url = 'http://localhost:8081/product/inWord/' + searchValue.value;

    fetch(`${url}?page=${page}&size=${defaultSearchSize}`)
        .then(response => {
            if(response.status == 404){
                drawAlert("No products to show by this search");
            } else if(response.status >= 400){
                throw new Error("Bad response from server");
            }
            return response.json()
        })
        .then(data => {
            document.querySelector('.products-block').innerHTML = '';
            data.items.forEach(product => {
                createProductCard(product);
            });
            totalPages = Math.ceil(data.total / data.size);
            if (totalPages === 0){
                totalPages = 1
            }
            currentPage.textContent = `Page ${data.page} of total ${totalPages}`;
        })
        .catch(error => {
            alert("No products to show by this search");
        });
}

function createProductCard(product) {
    let newRow = document.createElement("div");
    newRow.classList.add("row");

    let newElem = document.createElement("div");
    newElem.classList.add("col-md-4");
    newRow.appendChild(newElem);

    newElem.innerHTML = `
        <div class="product-cart mb-4">
            <img src="${product.image}" class="card-img-center" alt="${product.name}">
            <div class="card-body">
                <h5 class="card-title">${product.name}</h5>
                <p class="card-text">Price initialPrice: ${product.initialPrice} сом</p>
                <p class="card-text">brand: ${product.brand.name}</p>
                <p class="card-text">discount: ${product.discount}</p>
                <p className="card-text">currentPrice: ${product.currentPrice}</p>
                <p class="card-text">category: ${product.category.name}</p>
                <form action="/addItem" method="post" id="addItemCart">
                    <input type="hidden" id="name" name="name" value="${product.name}"/>
                    <button class="btn btn-primary" type="submit">Add to cart</button>
                </form>
                
            </div>
        </div>`;

    let productsBlock = document.querySelector(".products-block");
    let lastRow = productsBlock.querySelector(".row:last-of-type"); // находим последний ряд

    if (lastRow && lastRow.childElementCount < 3) {
        lastRow.appendChild(newElem);
    } else {
        productsBlock.appendChild(newRow);
    }
    let pagination = document.getElementById('pagination');
    if(pagination.getAttribute("style") == 'pagination'){
        pagination.setAttribute("style", "display: none");
    } else {
        pagination.setAttribute("style", "");
    }}

nextButton.addEventListener('click', () => {
    if (page < totalPages - 1) {
        page++;
        displayProducts();
    }
});

prevButton.addEventListener('click', () => {
    if (page > 0) {
        page--;
        displayProducts();
    }
});

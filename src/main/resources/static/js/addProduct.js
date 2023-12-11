function loadBrands(){
    let brandsBlock = document.getElementsByClassName('.brand-block');
     fetch('http://localhost:8081/brand/get')
        .then(response => {
            if(response.status == 404){
                brandsBlock.innerText = "No brands to show by this search";
            } else if(response.status >= 400){
                brandsBlock.innerText = "Bad response from server";
            }
            return response.json()
        })
        .then(data => {
            brandsBlock.innerHTML = '';
            data.forEach(brand => {
                drawBrandName(brand);
            });
        })
        .catch(error => {
            brandsBlock.innerText = "Bad response from server";
        });
}
function drawBrandName(brand){
    let brandsBlock = document.getElementById('brand-block');
    let brands = document.createElement('div');
    brands.classList.add("brands");
    brands.innerHTML = `
        <p class="brandName">${brand.name}</p>
        <input type="radio" name="brand" class="brand-checkbox" id="brand-${brand.id}" value="${brand.id}">
    `;
    brandsBlock.appendChild(brands);
}

function loadCategories(){
    let catBlock = document.getElementsByClassName('.category-block');
    fetch('http://localhost:8081/category/get')
        .then(response => {
            if(response.status == 404){
                catBlock.innerText = "No category to show by this search";
            } else if(response.status >= 400){
                catBlock.innerText = "Bad response from server";
            }
            return response.json()
        })
        .then(data => {
            catBlock.innerHTML = '';
            data.forEach(cat => {
                drawCatName(cat);
            });
        })
        .catch(error => {
            catBlock.innerText = "Bad response from server";
        });
}
function drawCatName(cat){
    let catBlock = document.getElementById('category-block');
    let category = document.createElement('div');
    category.classList.add("categories");
    category.innerHTML = `
        <p class="brandName">${cat.name}</p>
        <input type="radio" name="category" class="category-checkbox" id="category-${cat.id}" value="${cat.id}">
    `;
    catBlock.appendChild(category);
}

// for later version i need here to get user from local storage if there is a user then i will continue
//let user = JSON.parse(sessionStorage.user || null
//if user is present
//window.onload = () => {
//  location.replace('/login'); }
//otherwise location.replace('/addProduct');

///price inputs
const actualPrice = document.querySelector('#actual-price');
const discountPercentage = document.querySelector('#discount');
const sellingPrice = document.querySelector('#sell-price');

discountPercentage.addEventListener('input', ()=>{
    if(discountPercentage.value > 100){
        discountPercentage.value = 90;
    } else {
        let discount = actualPrice.value * discountPercentage.value / 100;
        sellingPrice.value = actualPrice.value - discount;
    }
})
sellingPrice.addEventListener('input', ()=> {
    let discount = (sellingPrice.value / actualPrice.value) * 100;
    discountPercentage.value = discount;
})
//upload image DISPLAY
var loadImage = function(event) {
    const inputFile = document.getElementById('first-file-upload-btn').files[0];
    let outputImage = document.getElementById('displayFirstImage');
    outputImage.src = URL.createObjectURL(inputFile);
    outputImage.onload = function() {
        URL.revokeObjectURL(outputImage.src) // free memory
    }
    outputImage.setAttribute("style", "width: 100px; height: 100px");

    let displayMainFrame = document.getElementById('displayMainFrame');
    displayMainFrame.src = URL.createObjectURL(inputFile);
    displayMainFrame.onload = function() {
        URL.revokeObjectURL(displayMainFrame.src) // free memory
    }
    displayMainFrame.setAttribute("style", "width: 500px; height: 500px");

};

// var loadImage = function(event) {    for further development with AWS S3
//     const inputFile = document.getElementById('first-file-upload-btn');
//     let label = document.querySelector(`label[for=${inputFile.id}]`)
//     let image = inputFile.files[0];
//     label.style.backgroundImage = image;
// };

const data = document.getElementsByClassName('.main-form');

function addProduct () {
    const data = new FormData();

    const name = document.getElementById('product-name').value;
    data.append('name', name);

    const description = document.getElementById('short-des').value;
    data.append('description', description);
    const image = document.getElementById('first-file-upload-btn').files[0];
    data.append('image', image);
    const initialPrice = document.getElementById('actual-price').value;
    data.append('initialPrice', initialPrice);
    const discount = document.getElementById('discount').value;
    data.append('discount', discount);
    const stock = document.getElementById('stock').value;
    data.append('storageQty', stock);
    const brand = document.getElementsByName('brand');
    brand.forEach(el => {
        if(el.checked){
            data.append('brand', el.value)
        }
    })
    const category = document.getElementsByName('category');
    category.forEach(el=>{
        if(el.checked){
            data.append('category', el.value)
        }
    })

    fetch('http://localhost:8081/product/add', {
        method: 'POST',
        body: data
    })
        .then(response => {
            if (response.status >= 400) {
                throw new Error("Bad response from server");
            }
            return response.json()
        })
        .then(data => {
            console.log(data);
            window.location.reload();
        })
        .catch(error => {
            console.error(error);
        });
}
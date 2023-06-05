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

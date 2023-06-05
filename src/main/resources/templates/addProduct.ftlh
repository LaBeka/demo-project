<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Seller: Add Product</title>
    <link rel="stylesheet" href="css/addProduct.css">
    <link rel="stylesheet" href="css/signup.css">
    <script src="https://code.jquery.com/jquery-3.6.4.js"></script>

</head>
<body>
<script>
    $(()=>{
        loadBrands();
        loadCategories();
    })
</script>
<img src="images/loader.gif" class="loader" alt="">

<div class="alert-box">
    <img src="images/error.png" class="alert.img" alt="">
    <p class="alert-msg"></p>
</div>

<img src="images/dark-logo.png" class="logo" alt="">
<div class="form">
    <input type="text" id="product-name" name="name" placeholder="product name">
    <input type="text" id="short-des" name="description" placeholder="short line about product">
    <textarea id="des" placeholder="detail description"></textarea>
<#--    product image-->
    <div class="product-info">
        <div class="product-image">
            <img id="displayMainFrame" alt="product image" style="display: none"/>
            <p class="text"></p></div>
        <div class="upload-image-sec">
        <#--        upload inputs-->
            <p class="text"><img src="images/camera.png" alt="">Upload image </p>
            <div class="upload-catalogue">
                <input type="file" class="fileupload" id="first-file-upload-btn" name="image" accept="image/*" onchange="loadImage(this)" hidden>
                    <img id="displayFirstImage" alt="your image" style="display: none"/>
                </input>
                <label for="first-file-upload-btn" class="upload-image"></label>

                <input type="file" class="fileupload" id="second-file-upload-btn" hidden>
                <label for="second-file-upload-btn" class="upload-image"></label>

                <input type="file" class="fileupload" id="third-file-upload-btn" hidden>
                <label for="third-file-upload-btn" class="upload-image"></label>

                <input type="file" class="fileupload" id="fourth-file-upload-btn" hidden>
                <label for="fourth-file-upload-btn" class="upload-image"></label>
            </div>
        </div>
        <div class="select-sizes">
            <p class="text">size available</p>
            <div class="sizes">
                <input type="checkbox" class="size-checkbox" id="xs" value="xs">
                <input type="checkbox" class="size-checkbox" id="s" value="s">
                <input type="checkbox" class="size-checkbox" id="m" value="m">
                <input type="checkbox" class="size-checkbox" id="l" value="l">
                <input type="checkbox" class="size-checkbox" id="xl" value="xl">
                <input type="checkbox" class="size-checkbox" id="xxl" value="xxl">
                <input type="checkbox" class="size-checkbox" id="xxxl" value="xxxl">
            </div>
        </div>
    </div>

    <div class="product-price">
        <input type="number" name="initialPrice" id="actual-price"  placeholder="actual price">
        <input type="number" name="discount" id="discount"  placeholder="discount">
        <input type="number" id="sell-price"  placeholder="sell price">
    </div>

    <input type="number" name="storageQty" id="stock" min="20" placeholder="item in stock (minimum 20)">

    <p>All Brands available</p>
    <div class="brand-block" id="brand-block"></div>

    <p>All Categories available</p>
    <div class="category-block" id="category-block"></div>

    <input type="checkbox" class="checkbox" id="tac" checked>
    <label for="tac">Clothing take 30% from your total sell</label>

    <div class="buttons">
        <button type="button" onclick="addProduct()" class="btn" id="add-btn">Add product</button>
        <button type="button" class="btn" id="save-btn">save draft</button>
    </div>
</div>

<script src="js/addProduct.js"></script>
<script src="js/token.js"></script>

</body>
</html>
$(document).ready(function () {
    updateBasketItemsCount();
    fillCurrentUsername();
})


function fillCurrentUsername() {
    $.ajax({
        type: "GET",
        url: "/getCurrentUser",
        dataType: 'text',
        success: function (data) {
            if (data) {
                $('#loggedInUsername').html("Current User: " + data);
            }
        }
    });
}

function filterDishes(responseDisplayMode) {

    var categoryId = $("#categoryFilter option:selected").val();
    if (categoryId) {
        sendResponceForFilter('/filterByCategory/' + categoryId, responseDisplayMode);
    } else {
        sendResponceForFilter('/getAllDishes', responseDisplayMode);
    }
}


function sendResponceForFilter(url, responseDisplayMode) {
    $.ajax({
        type: "GET",
        url: url,
        success: function (response) {
            displayDishes(responseDisplayMode, response);
        }
    });
}

function displayDishes(displayMode, response) {
    switch (displayMode) {
        case "MANAGING_DISHES":
            managingDishesDisplay(response);
            break;
        case "CATALOGUE":
            displayDishesForCatalogue(response);
            break;
    }
}

function displayDishesForCatalogue(response) {
    $('#dishGrid').empty();
    response.forEach(function (value) {
        $('#dishGrid').append(buildCatalogueCard(value));
    });
    if (response.length == 0) {
        $('#dishGrid').append(buildEmptyResultCard());
    }
}


function buildCatalogueCard(dish) {
    return '        <div class="overDiv">\n' +
        '                    <div>\n' +
        '                        <img  src=' + dish.photoPath + '>\n' +
        '                    </div>\n' +
        '                    <h4>' + dish.nameDish + '</h4>\n' +
        '                     <p>' + dish.category.nameOfCategory + '</p>\n' +
        '                    <span>' +
        '                     ' + dish.price.toLocaleString("en", {minimumFractionDigits: 2}) + '</span>\n' +
        '                    <p><a class="btn" href="' + '/getDishDetails/' + dish.dishId + '">Details</a></p>\n' +
        '        </div>'
}

function buildEmptyResultCard() {
    return '        <div class="text-center m-auto">\n' +
        '            <h3 style="color: red;">O_ops! Products not found &#9785;</h3>\n' +
        '            <img src="/img/notfound.png">\n' +
        '        </div>';
}

function changeDishAmount(dishId, currDishRow) {

    var dishesAmount = currDishRow.value;
    $.ajax({
        type: "POST",
        url: "/basket/changeDishesAmount",
        dataType: 'json',
        data: {
            "dishId": dishId,
            "dishesAmount": dishesAmount
        }
    });
    recalculateTotalPrice();
}

function recalculateTotalPrice() {
    var resultPrice = 0;
    $("#basketTblBody tr").each(function () {
        var price = parseFloat(this.children[2].children[0].textContent);
        var amount = parseInt(this.children[3].children[0].value);
        resultPrice += price * amount;
    });
    $("#totalPrice")[0].textContent = resultPrice.toLocaleString("en", {minimumFractionDigits: 2});
}

function deleteDishFromBasket(dishId, currentRow) {
    $.ajax({
        type: "POST",
        url: "/basket/deleteFromBasket",
        dataType: 'json',
        data: {
            "dishId": dishId
        }
    });
    currentRow.parentElement.parentElement.remove();
    if ($("#basketTblBody")[0].children.length == 0) {
        $("#basketForm").remove();
        $("#basketContent").append(buildEmptyBasketDiv());
    } else {
        recalculateTotalPrice();
    }
    updateBasketItemsCount();
}


function buildEmptyBasketDiv() {
    return '<div id="emptyBasket" th:if="${selectedDishMap == null or selectedDishMap.isEmpty()}">' +
        '<div class="text-center m-auto">' +
        '<h3 style="color: red;">O_ops! The basket is empty again &#9785;</h3>' +
        '<img src="/img/notfound.png">' +
        '</div>' +
        '</div>';
}

function updateBasketItemsCount() {
    $.ajax({
        type: "GET",
        url: "/basket/basketAmount",
        dataType: 'json',
        success: function (response) {
            if (response) {
                $('#itemCount').html(response).css('display', '');
            } else {
                $('#itemCount').html(response).css('display', 'none');
            }
        }
    });
}

$(document)
    .on('click', 'form button[type=submit]', function (e) {
        switch (e.target.id) {
            case "saveCategory":
                validateOptionForm(e);
                break;
            case "saveDish":
                validateNeedyForm(e);
                break;
        }
    });

function validateOptionForm(e) {
//maybe ERROR : same name
    var optionNameText = $.trim($("#categoryName").val());
    var isValid = optionNameText.length > 0 && optionNameText.length < 257;
    if (!isValid) {
        e.preventDefault();
        $("#nameEmptyError").text("Please, enter valid name: not empty and no more than 256 chars!");
        $("#categoryName").css("border-color", "red");
    }
}

function validateNeedyForm(e) {
    var formIsValid = true;
    //maybe error
    $(".product-field").each(function (i, field) {
        var fieldValue = field.children[0].value;
        if (!fieldValue) {
            fillErrorMessage(field);
            formIsValid = false;
        }
    });
    if (!formIsValid) {
        e.preventDefault();
    }
}

function fillErrorMessage(field) {
    var classList = field.parentElement.children[2].classList;
    if (classList.contains("error-hidden")) {
        field.parentElement.children[2].classList.remove("error-hidden");
        field.children[0].classList.add("error-border");
    }
}


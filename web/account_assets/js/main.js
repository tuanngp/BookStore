// Thêm các hàm xử lý sự kiện tương ứng cho Người dùng
function showUserForm() {
    document.getElementById("userForm").style.display = "block";
}

function cancelUserForm() {
    document.getElementById("userForm").style.display = "none";
}

function editUser(userId) {
    var row = document.querySelector(`[data-user-id="${userId}"]`);
    
    var id = row.dataset.userId;
    var username = row.dataset.userUsername;
    var password = row.dataset.userPassword;
    var name = row.dataset.userName;
    var email = row.dataset.userEmail;
    var phone = row.dataset.userPhone;
    var address = row.dataset.userAddress;
    var role = row.dataset.userRole;
    var isActive = row.dataset.userIsActive;    

    document.getElementById("userId").value = id ;
    document.getElementById("username").value = username;
    document.getElementById("password").value = password;
    document.getElementById("name").value = name;
    document.getElementById("email").value = email;
    document.getElementById("phone").value = phone;
    document.getElementById("address").value = address;
    document.getElementById("role").value = role;
    document.getElementById("isActive").value = isActive;
    
    console.log(username, password, name, email, phone, address, role, isActive);
    showUserForm();
}

function addBook() {
    resetBookForm();
    document.getElementById("action").value = "add";
    document.getElementById("bookForm").style.display = "block";
}

function cancelBookForm() {
    document.getElementById("bookForm").style.display = "none";
}

function editBook(bookId) {
    var row = document.querySelector(`[data-book-id="${bookId}"]`);
    document.getElementById("action").value = "update";
    
    var authorId = row.dataset.bookAuthodId;
    var authorName = row.dataset.bookAuthodName;
    var birthday = row.dataset.bookBirthday;
    var bio = row.dataset.bookBio;
    var publisherId = row.dataset.bookPublisherId;
    var publisherName = row.dataset.bookPublisherName;
    var establishedDate = row.dataset.bookDateEstablished;
    var id = row.dataset.bookId;
    var title = row.dataset.bookTitle;    
    var genre = row.dataset.bookGenre;    
    var description = row.dataset.bookDescription;    
    var quantity = row.dataset.bookQuantity;    
    var price = row.dataset.bookPrice;    
    var image = row.dataset.bookImage;    
    
    document.getElementById("authorId").value = authorId;
    document.getElementById("authorName").value = authorName;
    document.getElementById("birthday").value = birthday;
    document.getElementById("bio").innerHTML = bio;
    
    document.getElementById("publisherId").value = publisherId;
    document.getElementById("publisherName").value = publisherName;
    document.getElementById("establishedDate").value = establishedDate;
    
    document.getElementById("bookId").value = id;
    document.getElementById("bookTitle").value = title;
    document.getElementById("genre").value = genre;
    document.getElementById("description").innerHTML = description;
    document.getElementById("quantity").value = quantity;
    document.getElementById("price").value = price;
    document.getElementById("image").value = image;

    console.log(authorId,authorName, birthday, bio, publisherId, publisherName, establishedDate, id, title, genre, description, quantity,price, image);
    // Display the bookForm
    document.getElementById("action").value = "update";
    document.getElementById("bookForm").style.display = "block";
}

function resetBookForm() {
    document.getElementById("authorId").value = "";
    document.getElementById("authorName").value = "";
    document.getElementById("birthday").value = "";
    document.getElementById("bio").innerHTML = "";
    
    document.getElementById("publisherId").value = "";
    document.getElementById("publisherName").value = "";
    document.getElementById("establishedDate").value = "";
    
    document.getElementById("bookId").value = "";
    document.getElementById("bookTitle").value = "";
    document.getElementById("genre").value = "";
    document.getElementById("description").innerHTML = "";
    document.getElementById("quantity").value = "";
    document.getElementById("price").value = "";    
    document.getElementById("image").innerHTML = "";
}


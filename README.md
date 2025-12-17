# 💿🎵 Jenver's Record Store 🎵💿

This project is a full-stack e-commerce application built as a capstone project. It provides a RESTful API and a client-facing website for browsing vinyl records, managing products, handling user authentication, and supporting shopping cart functionality.

The application demonstrates backend development using Java, Spring Boot, and MySQL, along with frontend integration using a static website that consumes the API.

---


## 📌 Features

---

### 🔐 Authentication
- Secure user registration and login using JWT-based authentication
- Role-based access control for users and administrators

### 💸 Product Operations
- Browse all products
- Search and filter products by category, price range, and sub category
- Full CRUD operations for products and categories (Admin-only)

### 🛒 Shopping Cart
- TBD

### 👤 User Profile
- TBD

### 🐛 Bug fixes
- Improved product search accuracy
- Fixed issues related to price filtering
- Prevented duplicated products during updates

---

## Phases Overview

---

### Phase #1 - CategoriesController
Implemented RESTful API endpoints to manage products categories with full CRUD functionality:
- `GET /categories`: Retrieve all categories
- `GET /categories/{id}`: Retrieve a category by id
- `POST /categories/{id}`: Add a new category (admin-only)
- `PUT /categories/{id}`: Update a category (admin-only)
- `DELETE /categories/{id}`: Delete a category (admin-only)


---
### Phase #2 - Bug Fixes
**BUG #1**: Users have reported that the product search functionality is returning incorrect results.

**How I fixed it**: Corrected inaccurate product search results by improving SQL query logic and properly handling query parameters.

![img.png](images/BugFix1.png)

---

**BUG #2**: Some users have also noticed that some of the products seem to be duplicated. For example, a laptop is listed 3 times, and it appears to be the same product, but there are slight differences, such as the description or the price. If you look at the 3 laptops you notice that they are the same product. This laptop has been edited twice, the first time you updated the price, the second update was to the description. It appears that instead of updating the product, each time you tried to update, it added a new product to the database.

**How I fixed it**: Resolved duplication issues during product updates by ensuring existing products are updated instead of creating entries.

![img.png](images/BugFix2.png)

---

## Interesting Piece of Code:
``` java
// check if input is in the menu items
    public static boolean isValid(String input, String[] validList) {
        // traverses the list of the menu items to check if item is in menu items
        for (String valid : validList) {
            // equalsIgnoreCase compares two strings ignoring difference in string
            if (valid.equalsIgnoreCase(input.trim())) return true; //if item is in the list, return true
        } // trim removes spaces before and after the words
        return false; // if item is not in the menu items list, it is invalid
    }
```

Why it's interesting:

---

## Author

**Jenver Fernandez**

📍 Seattle, Washington

## References

Van Putten, M. (2025). LTCA Instructor Year Up United.

OpenAI. (2025). ChatGPT (GPT-5) [Large language model]. https://chat.openai.com/ README.md file assistance

---
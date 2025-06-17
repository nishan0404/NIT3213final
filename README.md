# 📚 NIT3213 Final Assignment - Book Dashboard App

This is a mobile application developed for the **NIT3213** course final assignment. The app allows users to **log in**, view a **list of books fetched from a protected API**, and view **details of selected books**. It demonstrates best practices in Android development using **Kotlin**, **Retrofit**, **Hilt**, and **MVVM architecture**.

---

## ✨ Features

- 🔐 User Login with token-based authentication  
- 📚 Dashboard with a list of books (from API)  
- 📖 Book Details screen with full info  
- 💉 Hilt for Dependency Injection  
- 🌐 Retrofit for API calls  
- 🧪 Unit Tests for JSON parsing and ViewModels  
- ✅ Follows Clean Architecture and SOLID principles  

---

## 📲 App Screens & Flow

### 1. Login Screen
- User interface with username and password fields  
- Username: Student’s first name  
- Password: Student ID in the format `s12345678`  
- Makes a **POST** request to the appropriate auth endpoint based on class location  
- Displays error messages for unsuccessful login attempts  
- Navigates to the Dashboard screen on successful login  

### 2. Dashboard Screen
- Uses a **RecyclerView** to display a list of entities fetched from the dashboard endpoint  
- Uses the `keypass` received from the login response to authenticate the **GET** request  
- Each RecyclerView item shows a summary of the entity (excluding the description)  
- Clicking an item opens the Details screen  

### 3. Details Screen
- Shows full details of the selected entity, including the description  
- User-friendly layout for clear information display  

---

## 🗂️ Project Overview

This project is an Android mobile application developed as part of the **NIT3213 - Mobile App UI Design and Implementation** final assignment at **Victoria University**.

The app is designed to:

- Authenticate users using token-based login.
- Fetch and display a list of books from a protected REST API.
- Show full details of selected books in a separate view.
- Demonstrate industry-standard best practices in Android app development.

### 📌 Key Technologies & Architecture

- **Kotlin** – Primary programming language  
- **MVVM Architecture** – Separation of concerns and scalable structure  
- **Hilt** – Dependency Injection  
- **Retrofit** – Networking and API communication  
- **RecyclerView** – List rendering  
- **ViewModel & LiveData** – State management  
- **Gson** – JSON parsing  
- **JUnit / Truth** – Unit testing  

This project showcases clean code, modular structure, and adherence to SOLID principles while meeting academic and practical learning outcomes.

## 👨‍💻 Author

**Nishan Bhattarai**  
Student, Victoria University  
GitHub: [@nishan0404](https://github.com/nishan0404)


# Ecommerce Website to Sell Personal Photo Prints by Nima Sherpa

## Project Name: SherpaSnaps

### Database Schema: https://github.com/sherpa2025/Capstone/wiki/Database-Schema
### Features

1. **Admin**
   - User Management
   - Product Management
   - Order Management
   - Implement Payment Integration
       - Process payments without storing sensitive payment info in the database
       - Use Stripe.js or Checkout for secure payment collection

2. **User**
   - New Registration
   - Browse Products
   - Cart and Wishlist Functionality
   - Checkout Functionality
       - Payment Integration with Stripe
   - Validation and Error Handling
       - Meaningful error responses to clients
       - Restriction to access unauthorized data

## Backend: Java Spring Boot

- Create APIs for User management (registration, login page (authentication), profile management)
- Create APIs for Product management (CRUD operations)
- Implement order management APIs (CRUD operations)
- Error Handling for APIs
- Establish connection to database
- Deploy into AWS

## Frontend: React TS

- UI interface for User management
- UI components for products (browsing, adding to cart, and managing the wishlist)
- Implement checkout functionality with Stripe
- Error handling page UI
- Deploy into Netlify/Vercel/AWS

## Database: MongoDB/MySQL

### Out-of-Scope (Would like to do if have time)

- Dockerizing both your Java Spring Boot backend and React TypeScript frontend and run them together using Docker Compose.
- Supplier/Vendor account management


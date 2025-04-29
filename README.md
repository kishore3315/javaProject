# Full_Stack_Java

E-Commerce Order Management

Entities:
• Customer (id, name, email, orders_list)
• Order (id, order_date, total_price, customer_id, items_list)
• Item (id, name, price, quantity, order_id)

Tasks:
• Implement CRUD operations for Customer, Order, and Item.
• Implement one-to-many relationship (Customer → Order).
• Implement many-to-many relationship (Order ↔ Item).
• Use JPQL to fetch all orders placed by a specific customer.
• Use second-level caching (Ehcache) for fetching frequently accessed customers.

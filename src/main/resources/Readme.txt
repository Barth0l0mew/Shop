Function
1.  Зaщита веб приложения
2. Просмиотр товаров
3. Добавления товаров в корзину
4. Оформление заказов
5. Фильтрация. сортирвока товаров
6. Управление карзиной
7. Оповещение по email
8. Навигация
Dependecy
+ 1. Spring Devtools
+ 2. Spring Web
3. Security
+ 4. Thymeleaf
+ 5. Data JPA
6. FlyWay
+ 7. Lombok
+ 8. H2 Database
Entity
+ 1. Product
    - id
    - title
    - price
    - discription
    - categories

+ 2. User
    - id
    - username
    - password
    - email
    - roles
    - basket
+3. Role
    - id
    - rolename (User, Admin, Manager)

+ 4. Order
    - id
    - crteated date
    - last change date
    - completed date
    - address
    - user
    - status (new, cansel, paid, closed)
    - details (product, price,  amount)

+ 5. Category
    - id
    - title (categiry_name)

+ 6. Basket
    - id
    - user
    - details (product list )
7. Status
    - id
    - statusName (new, cansel, paid, closed)
+ 8. OrderDetails
    - id
    - order
    - amount
    - price

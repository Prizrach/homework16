CREATE TABLE User
(
  id       int AUTO_INCREMENT PRIMARY KEY,
  login    text NOT NULL,
  password text
);

CREATE TABLE SHOP_ORDER
(
  id          int AUTO_INCREMENT PRIMARY KEY,
  user_id     int,
  total_price double NOT NULL,
  CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES USER (ID)
);

CREATE TABLE Good
(
  id    int AUTO_INCREMENT PRIMARY KEY,
  title text   NOT NULL,
  price double NOT NULL
);

CREATE TABLE Order_Good
(
  id       int AUTO_INCREMENT PRIMARY KEY,
  order_id int,
  good_id  int,
  CONSTRAINT order_id FOREIGN KEY (order_id) REFERENCES SHOP_ORDER (ID),
  CONSTRAINT good_id FOREIGN KEY (good_id) REFERENCES GOOD (ID)
);

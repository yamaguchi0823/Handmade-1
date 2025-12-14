-- Handmade-1 DB schema (Phase1)

CREATE TABLE items (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description TEXT,
  image_path VARCHAR(255),
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL
);

CREATE TABLE item_variants (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  item_id BIGINT NOT NULL,
  color VARCHAR(50),
  size VARCHAR(50),
  price INT NOT NULL,
  stock INT NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  CONSTRAINT fk_item_variants_item
    FOREIGN KEY (item_id)
    REFERENCES items(id)
    ON DELETE CASCADE
);

CREATE TABLE sales (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  variant_id BIGINT NOT NULL,
  quantity INT NOT NULL,
  sale_date DATE NOT NULL,
  created_at DATETIME NOT NULL,
  CONSTRAINT fk_sales_variant
    FOREIGN KEY (variant_id)
    REFERENCES item_variants(id)
);

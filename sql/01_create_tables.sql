-- Handmade-1 DB schema
-- Phase1 Final Version
-- MySQL 8.x / InnoDB / utf8mb4

-- =====================
-- items
-- =====================
CREATE TABLE items (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  category VARCHAR(50) DEFAULT NULL,
  description TEXT,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;

-- =====================
-- item_variants
-- =====================
CREATE TABLE item_variants (
  id BIGINT NOT NULL AUTO_INCREMENT,
  item_id BIGINT NOT NULL,
  color VARCHAR(50) DEFAULT NULL,
  size VARCHAR(50) DEFAULT NULL,
  category VARCHAR(50) NOT NULL,
  quantity_per_set INT NOT NULL,
  cost_price INT DEFAULT NULL,
  price INT NOT NULL,
  stock INT NOT NULL DEFAULT 0,
  sku_code VARCHAR(50) DEFAULT NULL,
  image_filename VARCHAR(255) DEFAULT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,
  is_deleted TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  KEY fk_item_variants_item (item_id),
  CONSTRAINT fk_item_variants_item
    FOREIGN KEY (item_id)
    REFERENCES items (id)
    ON DELETE CASCADE
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;

-- =====================
-- sales
-- =====================
CREATE TABLE sales (
  id BIGINT NOT NULL AUTO_INCREMENT,
  variant_id BIGINT NOT NULL,
  quantity INT NOT NULL,
  sale_price INT NOT NULL,
  sale_date DATE NOT NULL,
  channel VARCHAR(50) DEFAULT NULL,
  memo VARCHAR(255) DEFAULT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY variant_id (variant_id),
  CONSTRAINT sales_ibfk_1
    FOREIGN KEY (variant_id)
    REFERENCES item_variants (id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_general_ci;

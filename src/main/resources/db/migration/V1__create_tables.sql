CREATE TABLE IF NOT EXISTS employee (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255),
    address VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS position (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        code VARCHAR(255),
    name VARCHAR(255)
    );
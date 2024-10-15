CREATE TABLE IF NOT EXISTS produto (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    nome TEXT NOT NULL,
    quantidade INTEGER NOT NULL DEFAULT 0,
    preco DECIMAL(10, 2) NOT NULL,
    promocao INT NOT NULL DEFAULT 0
);

INSERT INTO produto (nome, quantidade, preco, promocao) VALUES
    ('Laranja', 20, 2.50, 20),
    ('Banana', 30, 1.50, 10),
    ('Smartphone Nokia Brick', 20, 1200.00, 0),
    ('Notebook Gamer Apenas LED RGB', 10, 5250.50, 2);

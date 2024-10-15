CREATE TABLE IF NOT EXISTS produto (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    nome TEXT NOT NULL,
    quantidade INTEGER NOT NULL DEFAULT 0,
    preco DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS promocao (
    id INTEGER PRIMARY KEY,
    desconto REAL NOT NULL,
    produto_id INTEGER NOT NULL,
    tempo_inicio TIMESTAMP NOT NULL,
    tempo_fim TIMESTAMP NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);

INSERT INTO produto (nome, quantidade, preco) VALUES
    ('Laranja', 20, 2.50),
    ('Banana', 30, 1.50),
    ('Smartphone Nokia Brick', 20, 1200.00),
    ('Notebook Gamer Apenas LED RGB', 10, 5250.50);

INSERT INTO promocao(desconto, produto_id, tempo_inicio, tempo_fim) VALUES
    (0.20, 1, '2024-10-13 00:00:00', '2024-12-05 23:59:59'),
    (0.40, 2, '2024-09-02 00:00:00', '2025-02-21 23:59:59');

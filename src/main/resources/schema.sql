CREATE TABLE IF NOT EXISTS produto (
    id INTEGER PRIMARY KEY,
    nome TEXT NOT NULL,
    descricao TEXT NOT NULL,
    quantidade INTEGER NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    status TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS promocao (
    id INTEGER PRIMARY KEY,
    desconto REAL NOT NULL,
    produto_codigo INTEGER NOT NULL,
    tempoInicio TIMESTAMP NOT NULL,
    tempoFim TIMESTAMP NOT NULL,
    FOREIGN KEY (produto_codigo) REFERENCES produto(id)
);

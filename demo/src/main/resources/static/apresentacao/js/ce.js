const apiUrl = 'http://localhost:3000/produtos'; // URL da sua API

// Função para cadastrar um item
function cadastrarItem() {
    const nome = document.getElementById('itemInput').value;
    const preco = parseFloat(document.getElementById('precoInput').value);
    const quantidade = parseInt(document.getElementById('quantidadeInput').value);

    if (nome && !isNaN(preco) && !isNaN(quantidade)) {
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ nome, preco, quantidade }),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao cadastrar o item');
                }
                return response.json();
            })
            .then(data => {
                console.log('Item cadastrado com sucesso:', data);
                listarProdutos(); // Atualiza a lista de produtos
            })
            .catch(error => {
                console.error('Erro:', error);
            });
    } else {
        alert('Por favor, preencha todos os campos corretamente.');
    }
}

// Função para listar os produtos
function listarProdutos() {
    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            const estoqueList = document.getElementById('estoqueList');
            estoqueList.innerHTML = ''; // Limpa a lista atual
            data.forEach(produto => {
                const listItem = document.createElement('li');
                listItem.textContent = `${produto.nome} - Preço: R$ ${produto.preco.toFixed(2)} - Quantidade: ${produto.quantidade}`;

                // Botões para excluir e mover para carrinho
                const excluirBtn = document.createElement('button');
                excluirBtn.textContent = 'Excluir';
                excluirBtn.onclick = () => excluirItem(produto.id);

                const moverBtn = document.createElement('button');
                moverBtn.textContent = 'Mover para Carrinho';
                moverBtn.onclick = () => moverParaCarrinho(produto.id);

                listItem.appendChild(excluirBtn);
                listItem.appendChild(moverBtn);
                estoqueList.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Erro ao listar os produtos:', error);
        });
}

// Função para excluir um item
function excluirItem(id) {
    fetch(`${apiUrl}/${id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao excluir o item');
            }
            console.log('Item excluído com sucesso');
            listarProdutos(); // Atualiza a lista de produtos
        })
        .catch(error => {
            console.error('Erro ao excluir o item:', error);
        });
}

// Função para mover o item para o carrinho
function moverParaCarrinho(id) {
    fetch(`${apiUrl}/${id}/moverCarrinho`, {
        method: 'PUT', // Ou outro método que sua API use
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao mover o item para o carrinho');
            }
            console.log('Item movido para o carrinho com sucesso');
            listarProdutos(); // Atualiza a lista de produtos
        })
        .catch(error => {
            console.error('Erro ao mover o item para o carrinho:', error);
        });
}

// Função para exibir/ocultar as opções com a seta
function toggleOpcoes() {
    const opcoesContainer = document.getElementById('opcoesContainer');
    opcoesContainer.classList.toggle('hidden');
    const toggleBtn = document.getElementById('toggleBtn');
    toggleBtn.innerText = opcoesContainer.classList.contains('hidden') ? '▼' : '▲';
}

// Event listener para o botão de alternar as opções
document.getElementById('toggleBtn').addEventListener('click', toggleOpcoes);

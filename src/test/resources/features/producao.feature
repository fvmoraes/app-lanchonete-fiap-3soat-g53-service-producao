# language: pt
Funcionalidade: Produto

  Cenario: Buscar Pedidos
    Quando efeturar uma requisicao para Buscar todos os pedidos
    Entao deve retornar uma lista com os pedidos cadastrados

  Cenario: Criar Pedidos
    Quando efetuar uma requisicao para Criar um Pedido
    Entao deve retornar o Pedido cadastrado

  Cenario: Buscar Poximo Pedido
    Quando efetuar requisicao para buscar o Proximo pedido da fila
    Entao deve retorna um Pedido

  Cenario: buscar Pedidos Por Id existente
    Quando efetuar requisicao de buscar Pedido por Id
    Entao retorna o pedido 

  Cenario: buscar Pedidos Por Id não existente
    Quando efetuar requisicao de buscar Pedido nao existente por Id
    Entao retorna null com status no content  

  Cenario: Busca Pedidos Por Status
    Quando efetuar requisicao de buscar Pedido por Status
    Entao retorna uma Lista de pedidos com o status

  Cenario: Atualiza Pedido Status
    Quando efetuar requisicao de atualizar o status de um pedido
    Entao retorna com o Pedido atualizado
   
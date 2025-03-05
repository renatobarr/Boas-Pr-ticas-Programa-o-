# Boas Práticas Programação

- CÓDIGO REFATORADO

- MELHORIAS:

✅ Reutilização do HttpClient: O cliente HTTP agora é instanciado uma vez no construtor e reutilizado.

✅ Reutilização do Gson: Em vez de criar um novo Gson a cada requisição, há uma única instância.

✅ Uso correto dos métodos HTTP: Em vez de .method("POST", ...), agora usamos .POST(...), tornando o código mais limpo.

✅ Tratamento adequado de exceções: Capturamos exceções específicas (IOException e InterruptedException) e encapsulamos em uma RuntimeException. Isso evita que o método precise declarar throws Exception, tornando o código mais robusto.

✅ Uso de List<Pet>: Substituímos Pet[] por List<Pet>, permitindo adicionar/remover pets dinamicamente.

✅ Encapsulamento correto: setId() foi removido, pois id deve ser imutável após a criação.

✅ Construtor completo: Agora é possível inicializar um abrigo com id.

✅ Métodos adicionarPet() e removerPet(): Facilitam a manipulação da lista de pets.

✅ Melhoria no toString(): Agora segue um formato mais legível e padronizado.

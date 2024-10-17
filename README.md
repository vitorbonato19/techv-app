# Refatoração do Projeto: Arquitetura Padrão Spring para Clean Architecture

## Visão Geral
Este documento tem a intenção de descrever as mudanças realizadas na estrutura do projeto durante a migração da arquitetura padrão do Spring para a Clean Architecture. O objetivo dessa refatoração é melhorar a modularidade, separação de responsabilidades e facilitar a manutenção e testes do código.

## Objetivos
- Implementar a Clean Architecture para isolar domínios, dados, interfaces e regras de negócio.
- Reduzir o acoplamento entre camadas.
- Aumentar a testabilidade do sistema.

---

### Descrição das Camadas:

#### 1. **Domain**
- Contém as **entidades** e **use cases**. 
- Responsável pela regra de negócio.
  
  **Exemplo:**
  - `User.java`: Representa o modelo de domínio do usuário.
  - `UserService.java`: Casos de uso relacionados a usuários.

#### 2. **Application**
- Contém a **orquestração** e **coordenação** dos casos de uso.
- Implementa interfaces que serão usadas pelas camadas externas (adapters).

  **Exemplo:**
  - `UserServiceImpl.java`: Implementação da lógica de uso de usuário.

#### 3. **Infrastructure**
- Contém as classes relacionadas a frameworks e ferramentas.
- **Persistência de dados**, **segurança**, e **configurações do Spring** ficam nesta camada.

  **Exemplo:**
  - `UserRepository.java`: Interface de repositório de usuário.
  - `DatabaseConfig.java`: Configuração de banco de dados.

#### 4. **Adapter**
- Contém os **controladores**, **implementações de repositórios** e outras classes externas que interagem com o sistema.
- Exposição das APIs e interação com as interfaces definidas nas camadas de aplicação e domínio.

  **Exemplo:**
  - `UserController.java`: Controlador REST para usuários.
  - `JpaUserRepository.java`: Implementação da interface `UserRepository

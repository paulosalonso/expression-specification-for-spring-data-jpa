# Expression Specification for Spring Data JPA
Implementation of Spring Data JPA Specification based on [QueryDecoder](https://github.com/paulosalonso/query-decoder) project.

## Usage example

```java
Specification<Person> spec = ExpressionSpecification.of("nome[CT]:Paulo");
```

# Expression Specification for Spring Data JPA
Implementation of Spring Data JPA Specification based on [QueryDecoder](https://github.com/paulosalonso/query-decoder) project.

## Usage example

In this example, the repository returns all persons where name contains "Paulo".

```java
Specification<Person> spec = ExpressionSpecification.of("name[CT]:Paulo");

personRepository.findAll(spec);
```

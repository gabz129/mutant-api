# X-Men API Rest #
This api rest checks and generates a report base on dna results.
Its a Spring Boot app using CRUD and H2 in memory database. <br/>
Added code plugins: checkstyle, pmd, findbugs and jacoco


## Assumptions ##
* The size of the matrix should be NxN. 
* The min size of the matrix should be 4. A valid sequence should contain 4 characters
* If the sequence has 8 characters, it will be count as 2 sequences of 4 characters
* Allowed characters: A | C | T | G
* When there is no data, the report data will be zero. 

## Endpoint Examples ##
#### /api/mutant
```
curl -X POST \
  http://localhost:8080/api/mutant \
  -H 'content-type: application/json' \
  -d '{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACGG"]}'
```
#### /api/stats
```
curl -X GET \
  http://localhost:8080/api/stats
```

For live version go to: https://mutant-api.cfapps.io/swagger-ui.html

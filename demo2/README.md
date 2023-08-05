### 상품 재고 redis 데이터 구조  

재고가 설정되는 단위는 productItem. 
productItem 에 대한 상품 재고 관리 redis 데이터 구조를 어떻게 가져갈 것인가

`productItem:1 -> { name: "기간권", stock: 30, price: 35000 }`

key -> { field1: value1, field2: value2, ... } 형식 ==> HASH 자료 구조 
# investPlatform
투자 플랫폼 내맘대로 재구성    
전 근무처였던 T모사의 투자 플랫폼 어플리케이션중 일부인 고객가입 + 투자 api를 기억나는대로 재구성함    
네이티브쿼리 + 프로시저로 이루어져있던 DB를 JPA를 이용하여 구성하느라 마음대로 깔끔하게 구성이 되지 않았음    
에러처리부분은 협의점이 필요한부분이라 혼자 연습하는 동안엔 필요없다 생각되어 작성하지 않음    
일부 API는 DTO가 아니라 도메인객체를 따로 생성해 사용하였는데, 큰 이유는 없고 api request시에 Validate를 좀 편하게 하고 싶어서 그리하였음    
api마다 DO를 작성해야하는 불편함은 있지만 Validate가 편하고, 코드가 깔끔해짐    
물론 MSA 프로젝트에서는 큰 장점이 될것임    
분명 나중에 기억이 전혀 안 날것이기 때문에 swagger ui를 붙여두었음    
비지니스 흐름도는    
member/create > member/login > /account ? account/deposit > /invest (/fund가 선행되어야함)

## Getting Started

Just bootRun it on 80 port



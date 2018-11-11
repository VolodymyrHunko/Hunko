
var myObj = {
  "name":"John",
  "age":31,
  "pets":[
    { "animal":"dog", "name":"Fido" },
    { "animal":"cat", "name":"Felix" },
    { "animal":"hamster", "name":"Lightning" }
  ],
  "cars":[
    {"make": "Volvo", "color": "red"},
    {"make": "BMW", "color": "black", "year": 1964}
  ]
}
function getAge(){
    return myObj.age;
}

function getPet(index){
    return myObj.pets[index].animal;
}
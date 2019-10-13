namespace java com.louis.thrift

typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

struct Person{
    1: optional String username,
    2: optional int age,
    3: optional boolean married
}

service PersonService{
    Person getPersonByUsername(1: required String username)

    void savePerson(1: required Person person)

}
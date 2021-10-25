import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class API101TEST extends Specification {

    @Shared
    def client = new RESTClient( "$https://api-101.glitch.me")
}

    def 'возвращает массив пользователей' () {
        when: '(The GET request) API returned JSON data including an array of customers'
        def response = client.get( path : '/customers' )

        then: 'server returns 200 code (ok)'
        assert response.status == 200 : 'response code should be 200 when tried to return JSON data including an array of customers'
    }

    def 'возвращает 404 ошибку когда пытаемся получить данные о не зарегистрированном пользователе' () {
    when: '(The GET request) endpoint has the `id` contains invalid info'
    client.get( path : '/customer?id=4' )

    then: 'server returns 404 code (invalid info)'
    HttpResponseException e = thrown(HttpResponseException)
    assert e.response.status == 404: 'response code should be 404 when you use wrong credentials'
    }

    def 'добавление нового пользователя' () {
      when: '(The POST request) to add a new customer to the database'
      def response = client.get( path : '/customers' )

      then: 'server returns 200 code (ok)'
      assert response.status == 200 : 'response code should be 200 when tried to return JSON data including an array of customers'
    }


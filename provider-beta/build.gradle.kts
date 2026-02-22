dependencies {
    // Веб-сервер для REST API
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Клиент Eureka, чтобы сервис мог зарегистрироваться в реестре
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
}
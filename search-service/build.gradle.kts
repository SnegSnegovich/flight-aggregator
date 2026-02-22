dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    // Добавляем балансировщик нагрузки для работы с именами сервисов
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
}
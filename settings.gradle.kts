rootProject.name = "flight-aggregator"

// Подключаем наши будущие микросервисы
include("eureka-server")
include("api-gateway")
include("search-service")
include("provider-alpha")
include("provider-beta")
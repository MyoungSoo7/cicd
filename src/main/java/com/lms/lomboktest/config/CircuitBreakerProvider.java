package com.lms.lomboktest.config;

//
//@Configuration
//class CircuitBreakerProvider(
//        val circuitBreakerProperty: CircuitBreakerProperty
//) {
//    companion object {
//        const val CIRCUIT_REDIS: String = "CB_REDIS"
//    }
//
//    @Bean
//    fun circuitBreakerRegistry(): CircuitBreakerRegistry {
//        return CircuitBreakerRegistry.ofDefaults()
//    }
//
//    @Bean
//    fun redisCircuitBreaker(circuitBreakerRegistry:CircuitBreakerRegistry): CircuitBreaker {
//        return circuitBreakerRegistry.circuitBreaker(
//                CIRCUIT_REDIS, CircuitBreakerConfig.custom()
//                        .failureRateThreshold(circuitBreakerProperty.failureRateThreshold)
//                        .slowCallDurationThreshold(Duration.ofMillis(circuitBreakerProperty.slowCallDurationThreshold))
//                        .slowCallRateThreshold(circuitBreakerProperty.slowCallRateThreshold)
//                        .waitDurationInOpenState(Duration.ofMillis(circuitBreakerProperty.waitDurationInOpenState))
//                        .minimumNumberOfCalls(circuitBreakerProperty.minimumNumberOfCalls)
//                        .slidingWindowSize(circuitBreakerProperty.slidingWindowSize)
//                        .ignoreExceptions(StockManageException::class.java)   // 화이트리스트로 서킷 오픈 기준 관리
//                .permittedNumberOfCallsInHalfOpenState(circuitBreakerProperty.permittedNumberOfCallsInHalfOpenState)
//                .build()
//        )
//    }
//}

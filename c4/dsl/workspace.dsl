workspace "Currency conversion" {

    !identifiers hierarchical

    model {
        u = person "User"
        ss = softwareSystem "Контур банка" {
            be = container "BPMN engine" {
                tags "bpmn"
                rest = component "REST API"
                cam = component Camunda {
                    tags "bpmn"
                }
                kl = component "Kafka listener" {
                    tags "kafka"
                }
            }
            cs = container "Currency service"
            ls = container "Limit reference service"
            os = container "OTP service"
            as = container "Account service"
            db = container "SQL database" {
                tags "Database"
            }
            mb = container "Брокер сообщений" {
                tags "message broker"
            }


            be.rest -> be.cam "Запуск процесса"
            cs -> be.cam "Предоставление курса валют"
            be.cam -> cs "Расчет транзакции"
            be.cam -> ls "Проверка лимитов"
            be.cam -> ss.os "Запрос на создание OTP"
            os -> mb "Запись результата по прохождению OTP"
            be.kl -> mb "Получение результата OTP"
            be.kl -> be.cam "Коррелирование результата OTP с процессом"
            be.cam -> as "Дебетование исходного счета"
            be.cam -> as "Кредитование целевого счета"

            be.cam -> db "Чтение и запись"
        }

        u -> ss.be.rest "Запуск конвертации"
        u -> ss.os "Ввод OTP"

        ss.be.rest -> u "Предоставление информации по конвертациям"
        ss.as -> u "Предоставление информации по счетам"
    }

    views {
        systemContext ss {
            include *
            autolayout lr
        }

        container ss {
            include *
            animation {
                u
                ss.be
                ss.db
                ss.cs
                ss.ls
                ss.mb
                ss.os
                ss.as
            }
        }

        component ss.be {
            include *
            animation {
                u
                ss.be.rest
                ss.be.cam
                ss.db
                ss.cs
                ss.ls
                ss.os
                ss.be.kl
                ss.mb
                ss.as
            }
        }

        styles {
            element "Element" {
                color #ffffff
            }
            element "Person" {
                background #8B8000
                shape person
            }
            element "Software System" {
                background #a62323
            }
            element "Container" {
                background #9a28f8
            }
            element "Database" {
                shape cylinder
                background #8f805a
            }
            element "Component" {
                background #00008B
            }
            element "message broker" {
                shape pipe
                background #8f805a
            }
            element "kafka" {
                background #8f805a
            }
            element "bpmn" {
                background #b34d4d
            }
        }
    }

    configuration {
        scope softwaresystem
    }

}

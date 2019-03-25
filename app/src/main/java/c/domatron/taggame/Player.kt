package c.domatron.taggame

data class Player ( var pid:    Int ?= null,
                    var user:   String = "",
                    var tid:    String = "",
                    var pass:   String = "",
                    var status: Int = 0)
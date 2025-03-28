rootProject.name = "gsandwiches"

include(":mono")

include(":common:initializer")
include(":common:mapper")
include(":common:result")

include(":core:api")
include(":core:domain")
include(":core:exceptions")
include(":core:persistence")

include(":sandwich:api")
include(":sandwich:application")
include(":sandwich:domain")
include(":sandwich:persistence")

rootProject.name = "gsandwiches"

include(":launchers:all-in-one")

include(":common:exceptionhandler")
include(":common:mapper")
include(":common:result")

include(":core:boot")
include(":core:spi")

include(":sandwich:api")
include(":sandwich:application")
include(":sandwich:domain")
include(":sandwich:persistence")

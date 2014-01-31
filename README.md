# SpringBoot Web Archetype

This is a simple starter app utilizing some of the soon-to-be-released SpringBoot goodness. 

More documentation coming soon... 

If you want to see it on Heroku, check out [http://springbootdemo.herokuapp.com]()

### Application Details

The following subpackages of `co.ntier.spring.boot.archetype.web` are of note:

* `config` - Configuration files. See the nifty embedded tomcat modifier
* `controller` - A few pre-baked controllers. Needs some polish but you'll get the idea.
* `model` - Some really simple entities. 
* `repo` - Simple *SpringData* JPA Repositories. I plan to add some custom implementations for posterity's sake.
* `svc` - A tier for our business logic. Currently there's not much to look at there. 

### Of note

The embedded Tomcat + Mongo session stuff is nifty, but only half baked. I recycled it from a project written nearly 2 years ago, but the concept is sound. 
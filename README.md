# BookRest

Ever needed a server to handle a book you have?
Ever wanted to read the book line by line using your browser?

Well this is the App for you.

## Rest

Our rest service requires two arguments, `server` and a config file path.

## Uploader

Our uploader 

How does your system work? (if not addressed in comments in source)

I built an uploader for some text in java and also a dropwizard service.

How will your system perform with a 1 GB file? a 10 GB file? a 100 GB file?

AerospikeDB is a shardable database, and easy to scale. If we do not have enough space, just add more vms!

How will your system perform with 100 users? 10000 users? 1000000 users?

The systems

What documentation, websites, papers, etc did you consult in doing this assignment? 

I looked at AS, Docker, and some Apache documentation for how the libraries worked.


What third-party libraries or other tools does the system use? How did you choose each library or framework you used?

I chose Dropwizad because I have worked with it before for a performant rest service.

I chose AS because our data is immutable and has a simple key value format. I also chose it because it is extremely scalable and as we add more data to it, it will handle load gracefully

How long did you spend on this exercise? If you had unlimited more time to spend on this, how would you spend it and how would you prioritize each item?

I spent about 6 hours on this. If I had more time, I would have worked to get docker working better, then logging and metrics.

I should have used Guice to do dependency management.

Finally, I would have made it possible to upload multiple books.

If you were to critique your code, what would you have to say about it?

I'd say I have a lot of comments and sometimes I make private functions for myself to make things easier (not done in this project) even though I am not being DRY.

I think that sometimes I can get lost in the details of how every component will work, and trying to anticipate failures.

I think I could have worked on the DAOs a little more and made some better exceptions.
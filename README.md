# cs263-languages-project

Jack Alexander (jalexanderqed)

Sierra Schwellenbach (sierrasch)

Our presentation can be found [here](https://docs.google.com/presentation/d/100y4HnFoegJ9sHjV0bkbZTX6DFzHCQh2H2haGKgPcgg/edit?usp=sharing)

# Instructions to Compile and Run

## AWS Lambda Client

The lambda client program--which invokes lambda functions, times them, and displays their results--is in the aws-access folder. To run, you must first provide credentials in a file "credentials.txt" in directory `src/main/resources/secrets`. The first line of the file should contain the access key ID for the AWS user that owns the function, and the second line should contain the access key secret. This folder is ignored by git, so there is no danger of it being committed.

Compiling and running requires Maven (mvn command), and the file `invoke.sh` can be used to easily compile and run the program. The arguments, in order are `<function name> <iterations> <iteration delay> <input int> <intput string>`. Iterations is the number of times the named Lambda function will be called, iteration delay is the delay between calls to the function, and the input int and string are provided as inputs to the Lambda function. However, the string is never used, so its value will not matter.

## AWS Lambda Function Creation

The programs for each language are in the folder <language>-lambda along with two scripts--"create_func.sh" and "update_func.sh"--for conveniently managing lambda functions. Note that for these scripts to work, the user must have installed the AWS CLI (command line interface) and provided credentials to it (for the same user that invokes the function through the client program above). The arguments to both scripts are the name of the Lambda function to be created or updated and the name of the program which is to be used WITHOUT the file extension.
  
## Running Functions Locally by Language

### Python

Python functions can be run extremely easily with `python <file> <input int> <input string>`. Note that the input string is not used in any of our programs (we put it in early in case we needed it and never did).

### Java

Java files can be run locally after building the project with `mvn package` using `java -cp target/lambda-java-1.0.jar jalexander.ninja.<class to be run> <input int> <input string>`.

### Go

Build executables with `GOOS=linux go build <filename>` (Go SDK must be installed) and run as executables with arguments `<input int> <input string>`.
  
## Lambda Function Creation by Language

### Python

Python Lambda functions are crated simply with a Python source file (as would be expected of an interpreted language), which is zipped before uploading to Lambda

### Java

Building the Java jar that is uploaded to Lambda is handled by Maven. All of our functions are compiled into one jar, and we simply specify the class to be used for each Lambda function.

### Go

Go functions are also compiled, and we simply zip and upload the resulting executable to Lambda.

# Weekly Reports

## Week 1:

Sierra is the superior coder clearly as she has finished the pi calculations for go while Jack has, and I quote "attempted to buy a couch". As such, Sierra will maintain the title of supreme coding champion and shall now be allowed to order Jack around for all eternity both in coding and in League. All hail Sierra

Aha! Another program started and conquered!! I am the master of go and python and before long I will surely conquer the java domain too! I possess inifinite wisdom and cleverness and will proceed to take over the world with them if unhindered. I will soon expand my slow takeover of the world with such functions as graph traversals, matrix multiplies, and prime number calculations. Jack will only be able to attempt to keep up with my intellect but doubtlessly fall short as many before him have done. But I do not blame him, of course. There have been very few able to even follow my train of thought let alone rival it. Mere mortals needn't be concerned with their own ineptitude as it is not so much their own short-coming as my own unparalleled ability. Tune in next time as I tackle some of the other major challenges of our time. 


## Week 2:

New challenges have come about this week. Namely, as we sat down to learn of the intricacies of AWS Lambda, my flatmate had the lack of foresight to mention to me that there was a new Harry Potter mobile game out. I was therefore thrust into the magical world of Hogwarts unceremoniously and had a lot of trouble focusing on lambda functions from then on. Fortunately, Jack was unswayed by the call of the magical world and diligently set up our Maven project for making AWS Lambda calls. By some magical happenstance we managed to accomplish our goals for the weak of learning about AWS Lambda and beginning to get it set-up. Nevertheless, I am loath to admit that the true MVP of the week would probably be Jack. On the coattails of my brilliant success last week, I will accept this secondary role. Next week, I will reclaim the heights of my successes in week 1 by somehow exercising some monolithic strength to ignore the callings of Harry Potter mobile games.

## Week 3:

Alas,it seems I forgot to update the README last week. Fear not, I will provide my executive summary now. In fact it is not alltogether suprising that updating the README did not make the list of tasks for the week as our main focus was getting Lambda to work. This, as it turns out, is quite the undertaking. It involved several hours of messing with permissions groups, formatting functions to arbitrary specifications, and other such finagling. I was also forced to update half the software on my computer as it turned out the version requirments for various things were far later than what I had installed. Nonetheless, we finally triumphantly got Lambda working in each of our chosen languages (Python, Go, and Java) completing the most irritating and frustrating part of our project thus far. Henceforth, most of our work shall consist of writing various benchmark functions for lambda into this new framework and performing the tests themselves. Onward and upward!

## Week 4:

Things are going well in the Lambda world. Jack has spent the week working on deserialization for the JSON we recieve from the Lamda function. I have been working on writing more test functions (specifically an nth prime function). This week has been a bit tricky as both of us were out of town on our normal thursday/friday worktimes. As such, we chose to attack the problem from two separate angles. I am happy to report that much of this project is coming together at this point. We shall see how the coming weeks go but I am confident that we will achieve both of our goals: benchmarking lambda in different languages and learning about lambda. Until then, I bid you adieu.

## Week 5:

I return with another episode of "The goings on in the lambda world". This week marks a momentous occasion with the first multithreaded (I know, crazy) program to be implemented in lambda ever... ok, maybe not ever, just by us for this class. Jack has succeeded in writing a multithreaded merge sort which will be celebrated by generations to come, no doubt. The minstrels will surely write songs about him and the birds shall sing words of praise from the mountain tops. Truly inspiring stuff from Jack this week. Meanwhile, I have had a fairly pedestrian week by comparison. I implemented nth prime in java, did a bit of general finagling with lambda, and tried to fix lambda which mysteriously broke on my computer. At the time I was fairly sure ALL of lambda everywhere must be broken, but after a lengthy thought process in which I concluded that this was a ludicrous notion, I was forced to accept the harsh reality that I had messed up my dev environment. Much fiddling, fidgeting, and unceremoniously smashing square pegs into round holes later, it is working once again. For how long? Only time will tell. I'll be keeping my fingers crossed. 

## Week 6:

This week we have well and truly lost our way. Doomed by the impending arrival of Jack's paramore and company, our apartment was abuzz with a flurry of cleaning, planning, and purchasing of rations. Working on our CS263 project fell wayside as our apartment was forced to accomodate a doubling of its normal inhabitant count. That is not to make light of our shortcomings. We failed to accomplish our tasks for the week and there will be much self-reflection on the topic of how exactly we allowed our Runtime Systems project to be forgotten in the silliness that was this week. Fear not! We have a plan on how to finish our project off in due course and we shall not fail in this endevour. With limited time left we must, as the kids are saying these days, "knuckle down". Our path, however, is clear. We must write one or two more programs in each of our chosen languages, collect some performance data, and analyze our little hearts out. Shouldn't be too hard. In fact, we're quite looking forward to it. 

## Week 7:

Time has marched forward as we all suspected it would but hoped it would do so at a pace more respectful of our sleep schedules. Unfortunately, we find ourselves amidst the week 9 craziness and have no choice but to rise to the challenge. We have but a weekend before dead week is upon us and unless we wish to be well and truly screwed, we must tackle the final challenges of this project, wrap it up in a neat bow, and prepare to present on monday. We have done our part this week to make sure we are on track to make a presentation of the ages and wow our classmates and professor with our knowledge of runtime systems and lambda-ness. This week we implemented multi-threaded merge sort in go and python to round off our collection of test programs. This weekend we will run our tests and perform the analysis that this whole quarter has been leading up to. On Monday at approximately 4pm GMT (11am PST) we will give the greatest presentation on Lambda functions to ever grace the halls of UCSB, and, if I do say so myself, probably the greatest presentation on any topic ever to grace the world. I have been told that this sounds slightly grandiose, but I can assure you it will seem modest by the time we are through. On Tuesday we will be preparing to make an appearance at the gala that will surely be thrown for us on the back of our great success and on Wednesday we will be shaking hands with the dozens of world leaders who will surely fly to Santa Barbara to meet the great Lambda connoisseurs. On Thursday, we will be at our apartment writing our paper on the topic. I know, it will be a bit of a letdown after the glamor of Tuesday and Wednesday, but we are, after all, the humblest of academics and must do our part to further the knowledge of human-kind. 

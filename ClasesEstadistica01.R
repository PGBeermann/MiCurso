Fstat <- function(){

    g1 <- rnorm(20,10,4)
    g2 <- rnorm(20,10,4)
    g3 <- rnorm(20,10,4)
    g4 <- rnorm(20,14,4)
    g5 <- rnorm(20,10,4)
    mse <- (var(g1)+var(g2)+var(g3)+var(g4)+var(g5))/5
    M <- (mean(g1)+mean(g2)+mean(g3)+mean(g4)+mean(g5))/5
    msb <- ((((mean(g1)-M)^2)+((mean(g2)-M)^2)+((mean(g3)-M)^2)+((mean(g4)-M)^2)+((mean(g5)-M)^2))/4)*20
    return( msb/mse)
}
# plot a histogram of F statistics and superimpose F distribution (4,72)
ff<-replicate(1000,Fstat())
dd<-qf(0.95,4,95)
x <- seq(0,6,0.01)
#hist(ff,freq=FALSE, ylim=ylim,xlim=c(0,6),breaks=100)
plot(density(ff),ylim=c(0,1.0),xlim=c(0,20))
curve(df(x,4,95),add=T,col="blue")
abline(v=dd,col="red")

p<-ff>dd
sum(p)

BMI<-rnorm(n=10000, m=24.2, sd=2.2) 
hist(BMI)

histinfo<-hist(BMI)
histinfo

hist(BMI, breaks=20, main="Breaks=20")
hist(BMI, breaks=5, main="Breaks=5")

hist(BMI, freq=FALSE, main="Density plot")

hist(BMI, freq=FALSE, xlab="Body Mass Index", main="Distribution of Body Mass Index", col="lightgreen", xlim=c(15,35),  ylim=c(0, .20),breaks=100)
curve(dnorm(x, mean=mean(BMI), sd=sd(BMI)), add=TRUE, col="darkblue", lwd=2)




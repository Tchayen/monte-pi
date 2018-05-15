# monte-pi
Approximation of π number using Monte Carlo method in Akka, spread between several physical nodes.

## Method
Appromixation of π number using Monte Carlo method goes as follows:
- we are using a circle of radius `R` inside a square of size `2R`
- the area of the circle will be `PI*R^2`
- area of the square will be `(2R)^2`
- therefore the ratio of the area of the square to the area of the circle will be `PI/4`
- it means that if we pick `N` points at random inside the square, approximately `N*PI/4` should fall inside the circle
- `PI = (4 * M) / N`, where `M` is number of points in the circle and `N` is the total number of points

## Model
Simulation is modelled using three types of actors:
- **Sampler** - used to create random samples
- **Verifier** - counts how many samples in each batch received from sampler were inside the circle
- **Reducer** - uses data from all reducers to calculate π

## Contributing
This project is a University assignment and must be completed within our team.

We don't believe there will be many people wanting to adjust our spaghetti, but in such case – feel free to open PR after `2018-07-01`.   

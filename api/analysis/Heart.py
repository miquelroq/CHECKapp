# Class responsible for converting an ECG signal into BPM, BREATH and DIFF
#

#import packages
import heartpy as hp
import matplotlib.pyplot as plt
import numpy as np

class Heart:

    # Sample rate is default BITalino rate (I think)
    def __init__(self, data, sample_rate=1000):
        self.data = data
        self.sample_rate = sample_rate

    # Plot the data
    def plot(self):
        plt.figure(figsize=(12,4))
        plt.plot(self.data)
        plt.show()

    # Extract metrics from data
    def run_analysis(self, plot=False):

        # Filter the signal
        filtered = hp.filter_signal(self.data, cutoff = 0.05, sample_rate = self.sample_rate, filtertype='notch')

        # Run analysis
        wd, m = hp.process(hp.scale_data(filtered), self.sample_rate)

        if plot:
            # Visualise in plot of custom size
            plt.figure(figsize=(12,4))
            hp.plotter(wd, m)

        # Display computed measures
        for measure in m.keys():
            print('%s: %f' %(measure, m[measure]))

    # 


def parse_file():
    f = open("tiago.txt", "r")

    ecg = list()

    for line in f:
        if line[0] == "#":
            continue

        line_data = int(line.split(sep="\t")[-5])

        ecg += [line_data]

    return ecg


dados = np.array(parse_file())


heart = Heart(dados, 1000)

# heart.plot()
heart.run_analysis(True)






# Class responsible for splitting an EEG signal into its different Band components
#

import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
import quickspikes as qs
from scipy.signal import find_peaks

def parse_file():
    f = open("miguel.txt", "r")

    eeg = list()

    for line in f:
        if line[0] == "#":
            continue

        line_data = int(line.split(sep="\t")[-4])

        eeg += [line_data]

    return eeg

class Bands:
    def __init__(self, data, sample_rate=1000):
        self.data = data
        self.sample_rate = sample_rate
        self.det = qs.detector(2000, 30)

    def get_bands(self):

        # Get real amplitudes of FFT (only in postive frequencies)
        fft_vals = np.absolute(np.fft.rfft(self.data))

        # Get frequencies for amplitudes in Hz
        fft_freq = np.fft.rfftfreq(len(self.data), 1.0/self.sample_rate)

        # Define EEG bands
        eeg_bands = {'Delta': (0, 4),
                    'Theta': (4, 8),
                    'Alpha': (8, 12),
                    'Beta': (12, 30),
                    'Gamma': (30, 45)}

        # Take the mean of the fft amplitude for each EEG band
        eeg_band_fft = dict()
        for band in eeg_bands:
            freq_ix = np.where((fft_freq >= eeg_bands[band][0]) &
                            (fft_freq <= eeg_bands[band][1]))[0]
            eeg_band_fft[band] = np.mean(fft_vals[freq_ix])

            # times = self.det.send(fft_vals[freq_ix])
            # peaks, _ = find_peaks(fft_vals[freq_ix])

            # plt.plot(fft_vals[freq_ix])
            # plt.scatter(times, fft_vals[freq_ix][times], color="red")
            # print(times)

            # plt.show()

        return eeg_band_fft

    def get_spikes(self):
        # TODO
        return

# bands = Bands(parse_file())

# print(bands.get_bands())

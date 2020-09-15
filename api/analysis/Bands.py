import matplotlib.pyplot as plt
import pandas as pd
import numpy as np


def parse_file():
    f = open("miguel.txt", "r")

    eeg = list()

    for line in f:
        if line[0] == "#":
            continue

        line_data = int(line.split(sep="\t")[-3])

        eeg += [line_data]

    return eeg


data = parse_file()

# Sampling rate (1000 Hz)
fs = 1000

# Get real amplitudes of FFT (only in postive frequencies)
fft_vals = np.absolute(np.fft.rfft(data))

# Get frequencies for amplitudes in Hz
fft_freq = np.fft.rfftfreq(len(data), 1.0/fs)

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

# Plot the data (using pandas here cause it's easy)

df = pd.DataFrame(columns=['band', 'val'])
df['band'] = eeg_bands.keys()
df['val'] = [eeg_band_fft[band] for band in eeg_bands]
ax = df.plot.bar(x='band', y='val', legend=True)
ax.set_xlabel("EEG band")
ax.set_ylabel("Mean band Amplitude")

ax.plot()
plt.show()


class Bands:
    def __init__(self, data, sample_rate=1000):
        self.data = data
        self.sample_rate = sample_rate

    def get_bands(self):

        # Get real amplitudes of FFT (only in postive frequencies)
        fft_vals = np.absolute(np.fft.rfft(self.data))

        # Get frequencies for amplitudes in Hz
        fft_freq = np.fft.rfftfreq(len(self.data), 1.0/fs)

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

        return eeg_band_fft

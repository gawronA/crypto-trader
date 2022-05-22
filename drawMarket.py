import pandas as pd
import numpy as np
import mplfinance as mpf

df_data = pd.read_csv('out/data.csv', \
    names=['date','open','close','high','low','avg'], \
    dtype={'open':np.float64,'close':np.float64,'high':np.float64,'low':np.float64,'avg':np.float64}, \
    index_col=0, \
    parse_dates=True)

df_data.index.name = 'data'

df_long = pd.read_csv('out/longData.csv', \
    names=['date','open','close','high','low'], \
    dtype={'open':np.float64,'close':np.float64,'high':np.float64,'low':np.float64}, \
    index_col=0, \
    parse_dates=True)

df_short = pd.read_csv('out/shortData.csv', \
    names=['date','open','close','high','low'], \
    dtype={'open':np.float64,'close':np.float64,'high':np.float64,'low':np.float64}, \
    index_col=0, \
    parse_dates=True)


adplts = [mpf.make_addplot(df_data['avg'], secondary_y=False)]
if not df_long.empty:
    adplts.append(mpf.make_addplot(pd.DataFrame(None, index=df_data.index).merge(df_long, how='left', left_index=True, right_index=True)['low'], type='scatter', markersize=100, marker='^'))
if not df_short.empty:
    adplts.append(mpf.make_addplot(pd.DataFrame(None, index=df_data.index).merge(df_short, how='left', left_index=True, right_index=True)['high'], type='scatter', markersize=100, marker='v'))

mpf.plot(df_data,type='candle', addplot=adplts)


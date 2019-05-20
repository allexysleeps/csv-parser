package FileParser.TransferQueue;

import PubSub.Message;

public interface TransferQueue {
  void addItem(Message item);
  Message getItem();
}
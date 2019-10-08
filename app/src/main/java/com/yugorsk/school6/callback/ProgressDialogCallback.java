package com.yugorsk.school6.callback;

public interface ProgressDialogCallback {
    void ProgressDialogSetMessage(String message);
    void ProgressDialogShow();
    void ProgressDialogDissmiss();
}

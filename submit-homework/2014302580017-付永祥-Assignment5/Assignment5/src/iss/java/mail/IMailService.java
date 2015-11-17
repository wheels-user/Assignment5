package iss.java.mail;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * ����һϵ���ʼ��շ�������
 */
public interface IMailService {
    /**
     * ��ʼ�����������е��ʼ�������
     * @throws MessagingException ��ʼ���������쳣
     */
    public void connect() throws MessagingException;

    /**
     * ���͵����ʼ�
     * @param recipient �ռ��������ַ
     * @param subject �ʼ�����
     * @param content �ʼ�����
     * @throws MessagingException �����ʼ�����
     */
    public void send(String recipient, String subject, Object content) throws MessagingException;

    /**
     * ѯ�ʷ������Ƿ������ʼ�����
     * @return ����ֵ��ָʾ�Ƿ������ʼ�
     * @throws MessagingException ѯ�ʷ���������
     */
    public boolean listen() throws MessagingException;

    /**
     * �����Զ��ظ������ݣ���ת��Ϊ�ַ���
     * ע���������뵽�����ⷽ��Ѱ�һظ��ʼ����ɣ�����һ��Ҫ�õ�����������
     * @param sender �Զ��ظ��ķ����������ַ
     * @param subject �Զ��ظ�������
     * @return �Զ��ظ��������ַ���
     * @throws MessagingException ��ѯ�ʼ��쳣
     * @throws IOException �����ʼ��쳣
     */
    public String getReplyMessageContent(String sender, String subject) throws MessagingException, IOException;
}

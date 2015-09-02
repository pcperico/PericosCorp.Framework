package PericosCorp.Framework.Core.Services.Interfaces;

public interface ILoggerService {
	public void LogSever(Exception ex,String path);
	public void LogInfo(String info,String path);
}
